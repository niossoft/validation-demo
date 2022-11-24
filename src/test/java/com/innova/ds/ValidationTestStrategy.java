package com.innova.ds;

import java.util.Map;

public interface ValidationTestStrategy {

    void assertResult(final Map<String, String> errMsgMapActual,
                      final Map<String, String> errMsgMapExpected);
}
