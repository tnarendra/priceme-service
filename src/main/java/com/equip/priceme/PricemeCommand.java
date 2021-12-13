package com.equip.priceme;

import com.equip.priceme.command.CommodityCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "priceme",
        description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {CommodityCommand.class}
)
public class PricemeCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricemeCommand.class);

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = {"-h", "--help"}, description = "...")
    boolean help;

    public PricemeCommand() {
    }

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(PricemeCommand.class, args);
    }

    public void run() {

        if (verbose || help) {
            LOGGER.info("\n\n**********************************************************************************************************************************\n" +
                    "*   Usage:                                                                                                                       *\n" +
                    "*   Execute Jar file: java -jar build/libs/priceme-0.1-all.jar <commodity-command> <product-name> <tons> <price-per-ton>         *\n" +
                    "*   Example: java -jar build/libs/priceme-0.1-all.jar commodity Lemon 12 34                                                      *\n" +
                    "*                                                                                                                                *\n" +
                    "*   Execute from command line: ./priceme commodity lemon 23 1                                                                    *\n" +
                    "**********************************************************************************************************************************\n");
        } else {
            LOGGER.info("\n\n*************************************************\n" +
                    "*  No arguments passed, pass -h or -v for help  *\n" +
                    "*************************************************\n");
        }
    }
}


/*@Command(name = "priceme", subcommands = {CommodityCommand.class})

class PricemeCommand implements Callable<Object> {

    private static int execute(Class<?> clazz, String[] args) {
        try (ApplicationContext context = ApplicationContext.builder(
                clazz, Environment.CLI).start()) {

            return new CommandLine(clazz, new MicronautFactory(context)).
                    setCaseInsensitiveEnumValuesAllowed(true).
                    setUsageHelpAutoWidth(true).
                    execute(args);
        }
    }

    public static void main(String[] args) {
        int exitCode = execute(PricemeCommand.class, args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Hello from verbose");
        return "Hello!";
    }
}*/
