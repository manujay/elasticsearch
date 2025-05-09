package com.springdemo.elasticsearch.events;


import com.springdemo.elasticsearch.Constant;
import com.springdemo.elasticsearch.Payload;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeRepositoryService;
    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        long milliSeconds = System.currentTimeMillis();
        return new ResponseEntity<>("{\"milliseconds\": \"" + milliSeconds + "\"}", HttpStatus.OK);
    }

    @GetMapping("/getInfo/{udevId}")
    public ResponseEntity<List<EventTypeDto>> getAllEventTypeInfo(@PathVariable String udevId) {
        List<EventTypeDto> list = eventTypeRepositoryService.getEventTypeInfo(udevId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Long> send(@RequestBody Payload info) {
        return new ResponseEntity<>(eventTypeRepositoryService
                .addEventInfo(EventTypeDto.map((i) -> new EventTypeDto(
                                info.getType(),
                                info.getInfo(),
                                info.getCreateTs(),
                                info.getUdevId()),
                        info)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{udevId}")
    public ResponseEntity<Long> delete(@PathVariable String udevId) {
        return new ResponseEntity<>(eventTypeRepositoryService.deleteUdevId(udevId), HttpStatus.OK);
    }

    @GetMapping("/moveToQueue/{udevId}")
    public ResponseEntity<String> moveToQueue(@PathVariable String udevId) {
        eventTypeRepositoryService.getEventTypeInfo(udevId)
                .forEach(e -> producerTemplate.sendBody(Constant.DIRECT_EVENT_TYPE_ROUTE, e));
        return ResponseEntity.ok("{\"movedToQueue\": \"" + udevId + "\"}");
    }

}
