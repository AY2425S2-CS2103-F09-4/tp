package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("tele/");
    public static final Prefix PREFIX_POSITION = new Prefix("pos/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_SKILL = new Prefix("s/");
    public static final Prefix PREFIX_OTHER = new Prefix("o/");
    public static final Prefix PREFIX_DUE_DATE = new Prefix("due/");
    public static final Prefix PREFIX_TASK_DESC = new Prefix("taskdesc/");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("taskint/");
    public static final Prefix PREFIX_TASK = new Prefix("task/");
}
