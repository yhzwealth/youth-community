package com.chuang.bootplus.base.interceptor;

import cn.hutool.cache.GlobalPruneTimer;
import cn.hutool.core.collection.CopiedIter;
import cn.hutool.core.lang.func.Func0;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
public class MyTimedCache<K, V> {
    private static final long serialVersionUID = 1L;
    protected Map<K, MyCacheObj<K, V>> cacheMap;
    private final StampedLock lock = new StampedLock();


    protected final Map<K, Lock> keyLockMap = new ConcurrentHashMap<>();


    protected int capacity;


    protected long timeout;


    protected boolean existCustomTimeout;


    protected AtomicLong hitCount = new AtomicLong();


    protected AtomicLong missCount = new AtomicLong();


    protected CacheListener<K, V> listener;

    private ScheduledFuture<?> pruneJobFuture;


    public MyTimedCache(long timeout) {
        this(timeout, new HashMap<>());
    }


    public MyTimedCache(long timeout, Map<K, MyCacheObj<K, V>> map) {
        this.capacity = 0;
        this.timeout = timeout;
        this.cacheMap = map;
    }

    public void put(K key, V object) {
        put(key, object, this.timeout);
    }


    public void put(K key, V object, long timeout) {
        long stamp = this.lock.writeLock();
        try {
            putWithoutLock(key, object, timeout);
        } finally {
            this.lock.unlockWrite(stamp);
        }
    }


    private void putWithoutLock(K key, V object, long timeout) {
        MyCacheObj<K, V> co = new MyCacheObj<>(key, object, timeout);
        if (timeout != 0L) {
            this.existCustomTimeout = true;
        }
        if (isFull()) {
            pruneCache();
        }
        this.cacheMap.put(key, co);
    }

    public boolean containsKey(K key) {
        long stamp = this.lock.readLock();

        try {
            MyCacheObj<K, V> co = this.cacheMap.get(key);
            if (co == null) {
                return false;
            }

            if (false == co.isExpired()) {
                return true;
            }
        } finally {
            this.lock.unlockRead(stamp);
        }


        remove(key, true);
        return false;
    }


    public long getHitCount() {
        return this.hitCount.get();
    }


    public long getMissCount() {
        return this.missCount.get();
    }


    public V get(K key, boolean isUpdateLastAccess, Func0<V> supplier) {
        V v = get(key, isUpdateLastAccess);
        if (null == v && null != supplier) {

            Lock keyLock = this.keyLockMap.computeIfAbsent(key, k -> new ReentrantLock());
            keyLock.lock();

            try {
                MyCacheObj<K, V> co = this.cacheMap.get(key);
                if (null == co || co.isExpired()) {
                    try {
                        v = supplier.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    put(key, v, this.timeout);
                } else {
                    v = co.get(isUpdateLastAccess);
                }
            } finally {
                keyLock.unlock();
                this.keyLockMap.remove(key);
            }
        }
        return v;
    }

    public V get(K key, boolean isUpdateLastAccess) {
        long stamp = this.lock.tryOptimisticRead();
        MyCacheObj<K, V> co = this.cacheMap.get(key);
        if (false == this.lock.validate(stamp)) {

            stamp = this.lock.readLock();
            try {
                co = this.cacheMap.get(key);
            } finally {
                this.lock.unlockRead(stamp);
            }
        }


        if (null == co) {
            this.missCount.getAndIncrement();
            return null;
        }
        if (false == co.isExpired()) {
            this.hitCount.getAndIncrement();
            return co.get(isUpdateLastAccess);
        }


        remove(key, true);
        return null;
    }

    public Iterator<V> iterator() {
        CacheObjIterator<K, V> copiedIterator = (CacheObjIterator) cacheObjIterator();
        return new CacheValuesIterator<>(copiedIterator);
    }

    public Iterator<MyCacheObj<K, V>> cacheObjIterator() {
        CopiedIter<MyCacheObj<K, V>> copiedIterator;
        long stamp = this.lock.readLock();
        try {
            copiedIterator = CopiedIter.copyOf(this.cacheMap.values().iterator());
        } finally {
            this.lock.unlockRead(stamp);
        }
        return new CacheObjIterator<>(copiedIterator);
    }


    public final int prune() {
        long stamp = this.lock.writeLock();
        try {
            return pruneCache();
        } finally {
            this.lock.unlockWrite(stamp);
        }
    }


    public int capacity() {
        return this.capacity;
    }


    public long timeout() {
        return this.timeout;
    }


    protected boolean isPruneExpiredActive() {
        return (this.timeout != 0L || this.existCustomTimeout);
    }


    public boolean isFull() {
        return (this.capacity > 0 && this.cacheMap.size() >= this.capacity);
    }


    public void remove(K key) {
        remove(key, false);
    }


    public void clear() {
        long stamp = this.lock.writeLock();
        try {
            this.cacheMap.clear();
        } finally {
            this.lock.unlockWrite(stamp);
        }
    }


    public int size() {
        return this.cacheMap.size();
    }


    public boolean isEmpty() {
        return this.cacheMap.isEmpty();
    }

    @Override

    public String toString() {
        return this.cacheMap.toString();
    }


    public MyTimedCache<K, V> setListener(CacheListener<K, V> listener) {
        this.listener = listener;
        return this;
    }


    public Set<K> keySet() {
        return this.cacheMap.keySet();
    }


    protected void onRemove(K key, V cachedObject) {
        CacheListener<K, V> listener = this.listener;
        if (null != listener) {
            listener.onRemove(key, cachedObject);
        }
    }


    private void remove(K key, boolean withMissCount) {
        MyCacheObj<K, V> co;
        long stamp = this.lock.writeLock();

        try {
            co = removeWithoutLock(key, withMissCount);
        } finally {
            this.lock.unlockWrite(stamp);
        }
        if (null != co) {
            onRemove(co.key, co.obj);
        }
    }


    private MyCacheObj<K, V> removeWithoutLock(K key, boolean withMissCount) {
        MyCacheObj<K, V> co = this.cacheMap.remove(key);
        if (withMissCount) {
            this.missCount.getAndIncrement();
        }
        return co;
    }

    protected int pruneCache() {
        int count = 0;
        Iterator<MyCacheObj<K, V>> values = this.cacheMap.values().iterator();

        while (values.hasNext()) {
            MyCacheObj<K, V> co = values.next();
            if (co.isExpired()) {
                values.remove();
                onRemove(co.key, co.obj);
                count++;
            }
        }
        return count;
    }


    public void schedulePrune(long delay) {
        this.pruneJobFuture = GlobalPruneTimer.INSTANCE.schedule(this::prune, delay);
    }


    public void cancelPruneSchedule() {
        if (null != this.pruneJobFuture) {
            this.pruneJobFuture.cancel(true);
        }
    }

    public void putWithoutUpdate(K key, V object, long timeout) {
        long stamp = this.lock.writeLock();
        try {
            MyCacheObj<K, V> co = cacheMap.get(key);
            if (co == null) {
                co = new MyCacheObj<>(key, object, timeout);
            }
            if (timeout != 0L) {
                this.existCustomTimeout = true;
            }
            if (isFull()) {
                pruneCache();
            }
            co.setValue(object);

            this.cacheMap.put(key, co);
        } finally {
            this.lock.unlockWrite(stamp);
        }
    }

    public static class MyCacheObj<K, V> implements Serializable {
        private static final long serialVersionUID = 1L;
        protected final K key;
        protected V obj;
        private volatile long lastAccess;
        protected AtomicLong accessCount = new AtomicLong();


        private final long ttl;


        protected MyCacheObj(K key, V obj, long ttl) {
            this.key = key;
            this.obj = obj;
            this.ttl = ttl;
            this.lastAccess = System.currentTimeMillis();
        }


        boolean isExpired() {
            if (this.ttl > 0L) {
                return (System.currentTimeMillis() - this.lastAccess > this.ttl);
            }
            return false;
        }


        V get(boolean isUpdateLastAccess) {
            if (isUpdateLastAccess) {
                this.lastAccess = System.currentTimeMillis();
            }
            this.accessCount.getAndIncrement();
            return this.obj;
        }


        public K getKey() {
            return this.key;
        }


        public V getValue() {
            return this.obj;
        }

        public void setValue(V obj) {
            this.obj = obj;
        }


        @Override
        public String toString() {
            return "CacheObj [key=" + this.key + ", obj=" + this.obj + ", lastAccess=" + this.lastAccess + ", accessCount=" + this.accessCount + ", ttl=" + this.ttl + "]";
        }
    }

    public static class CacheObjIterator<K, V>
            implements Iterator<MyCacheObj<K, V>>, Serializable {
        private static final long serialVersionUID = 1L;
        private final Iterator<MyCacheObj<K, V>> iterator;
        private MyCacheObj<K, V> nextValue;

        CacheObjIterator(Iterator<MyCacheObj<K, V>> iterator) {
            this.iterator = iterator;
            nextValue();
        }

        @Override
        public boolean hasNext() {
            return (this.nextValue != null);
        }

        @Override
        public MyCacheObj<K, V> next() {
            if (false == hasNext()) {
                throw new NoSuchElementException();
            }
            MyCacheObj<K, V> cachedObject = this.nextValue;
            nextValue();
            return cachedObject;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
        }

        private void nextValue() {
            while (this.iterator.hasNext()) {
                this.nextValue = this.iterator.next();
                if (!this.nextValue.isExpired()) {
                    return;
                }
            }
            this.nextValue = null;
        }
    }


    public static class CacheValuesIterator<V>
            implements Iterator<V>, Serializable {
        private static final long serialVersionUID = 1L;
        private final CacheObjIterator<?, V> cacheObjIter;

        CacheValuesIterator(CacheObjIterator<?, V> iterator) {
            this.cacheObjIter = iterator;
        }


        @Override
        public boolean hasNext() {
            return this.cacheObjIter.hasNext();
        }

        @Override
        public V next() {
            return (V) this.cacheObjIter.next().getValue();
        }

        @Override
        public void remove() {
            this.cacheObjIter.remove();
        }
    }

    public interface CacheListener<K, V> {
        void onRemove(K paramK, V paramV);
    }
}
