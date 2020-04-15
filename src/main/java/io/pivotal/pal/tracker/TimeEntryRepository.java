package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry t);

    public TimeEntry update(long id, TimeEntry t);

    public TimeEntry delete(long id);

    public TimeEntry find(long id);

    public List<TimeEntry> list();
}
