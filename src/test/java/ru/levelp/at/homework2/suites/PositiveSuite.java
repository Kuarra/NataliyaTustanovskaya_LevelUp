package ru.levelp.at.homework2.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.levelp.at.homework2.TicketTest;

@Suite
@IncludeTags({"positive"})
@SelectClasses(TicketTest.class)
public class PositiveSuite {
}
