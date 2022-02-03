package sample.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.spring.service.SampleService;

public class SampleServiceImpl implements SampleService {

    Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Override
    public void execute() {
        logger.info("Hello from service");
    }
}
