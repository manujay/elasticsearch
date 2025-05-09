package com.springdemo.elasticsearch.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Component
public class EventTypeInfoServiceImpl implements EventTypeService {

    Logger log = LoggerFactory.getLogger(EventTypeInfoServiceImpl.class.getName());

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public List<EventTypeDto> getEventTypeInfo(String udevId) {
        List<EventType> resultSets = eventTypeRepository.findByUdevId(udevId);
        log.debug("fetched {} getEventTypeInfo for udevId {}", resultSets.size(), udevId);
        return resultSets.stream().map(EventTypeDto::new).toList();
    }

    @Override
    public Long addEventInfo(EventTypeDto dto) {
        UUID uuid = UUID.nameUUIDFromBytes(dto.getUdevId().getBytes(StandardCharsets.UTF_8));
        int added = eventTypeRepository.createInfo(dto.getCreateTs(), uuid.toString());
        if (added > 0) {
            log.debug("adding event {} addEventInfo for udevId {} at createTs {}", dto.getType(), uuid, dto.getCreateTs());
            added = eventTypeRepository.updateInfo(dto.getEvent(), uuid.toString(), dto.getType());
        } else {
            EventType dao = new EventType(dto.getType(), dto.getType(), dto.getCreateTs(), dto.getCreateTs(), uuid.toString());
            return eventTypeRepository.save(dao).getId();
        }
        return Integer.toUnsignedLong(added);
    }

    @Override
    public Long deleteUdevId(String udevId) {
        log.debug("delete all events for udevId {}", udevId);
        List<EventType> deleted = eventTypeRepository.findByUdevId(udevId);
        deleted.forEach(d -> eventTypeRepository.deleteById(d.getId()));
        return Integer.toUnsignedLong(deleted.size());
    }
}
