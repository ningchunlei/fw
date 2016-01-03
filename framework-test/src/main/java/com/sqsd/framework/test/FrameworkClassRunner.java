package com.sqsd.framework.test;

import mockit.internal.startup.Startup;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FrameworkClassRunner extends SpringJUnit4ClassRunner {

    static { Startup.initializeIfPossible(); }

    public FrameworkClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
