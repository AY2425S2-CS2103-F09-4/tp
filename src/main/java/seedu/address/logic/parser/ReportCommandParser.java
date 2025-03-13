package seedu.address.logic.parser;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ReportCommandParser implements Parser<ReportCommand> {
    @Override
    public ReportCommand parse(String args) throws ParseException {
        // No arguments needed for now
        return new ReportCommand();
    }
}
