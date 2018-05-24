package com.github.gitsby.insert_perfomance.utils;

import com.github.gitsby.insert_perfomance.model.TestObject;
import org.springframework.stereotype.Component;

@Component
public class RandomEntity {

    public TestObject rndTestObject() {
        TestObject testObject = new TestObject();
        testObject.id = RND.str(32);
        testObject.column1 = RND.str(32);
        testObject.column2 = RND.str(32);
        testObject.column3 = RND.str(32);
        testObject.column4 = RND.str(32);
        testObject.column5 = RND.str(32);
        testObject.column6 = RND.str(32);
        return testObject;
    }
}
