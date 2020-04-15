package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> hashMap;
    long count;

    public InMemoryTimeEntryRepository() {
        hashMap = new HashMap<>();
        this.count = 0L;
    }

    @Override
    public TimeEntry create(TimeEntry t) {
        this.count += 1;
        t.setId(this.count);
        hashMap.put(t.getId(), t);
        return t;
    }

    @Override
    public TimeEntry update(long id, TimeEntry t) {
        if(hashMap.containsKey(id)) {
            t.setId(id);
            hashMap.replace(id, t);
            return hashMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        TimeEntry x = find(id);
        hashMap.remove(id);
    }

    @Override
    public TimeEntry find(long id) {
        return hashMap.getOrDefault(id, null);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(hashMap.values());
    }
}
