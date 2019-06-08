package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry entry = new TimeEntry(id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntryHashMap.put(id, entry);
        return entry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryHashMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryHashMap.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(long id) {
        timeEntryHashMap.remove(id);
    }
}
