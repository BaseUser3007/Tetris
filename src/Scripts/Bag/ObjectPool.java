package Scripts.Bag;

import Scripts.Interfaces.IPoolable;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectPool<T extends IPoolable> {

    protected List<T> _pool;

    public ObjectPool() {
        _pool = new ArrayList<>();
        initPool();
    }

    protected abstract void initPool();
    protected abstract T createObject();

    public T get() {
        for (T obj : _pool) {
            if (!obj.isActive()) {
                obj.reset();
                obj.setActive(true);
                return obj;
            }
        }
        T newObj = createObject();
        newObj.setActive(true);
        _pool.add(newObj);
        return newObj;
    }

    public void returnToPool(T obj) {
        obj.setActive(false);
    }

    public void returnAll() {
        for (T obj : _pool) {
            obj.setActive(false);
        }
    }

    public List<T> getAll() {
        return _pool;
    }
}