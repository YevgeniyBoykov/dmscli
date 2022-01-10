package com.bjen.dmscli;

import org.apache.commons.cli.*;
import java.sql.SQLException;
import java.util.Map;


class DmscliMain {

    public static void main(String[] args) throws SQLException {
        ConfigReader configReader = new ConfigReader();
        Map<String, String> configFile = configReader.ConfigReader();

        String host = configFile.get("db_host");
        String port = configFile.get("port");
        String dbName = configFile.get("db_shema");

        DBWorker dbWorker = new DBWorker("jdbc:mysql://"+host+ ":"+ port + "/" + dbName, configFile.get("login"), configFile.get("password"));

        Options options = new Options();

        options.addOption("help", false, "help." );
        options.addOption("dnslist", false, "List of enable DNS Server." );
        options.addOption("dnsstatus", true, "Display status od some server.");
        options.addOption("dnsblock", true, "URL address witch add to blockList.");

        try{
            CommandLine line = new DefaultParser().parse( options, args );

            if( line.hasOption("help")){
                System.out.println();
            }

            if( line.hasOption( "dnslist" ) ) {
                dnslist();
            }
            if(line.hasOption("dnsstatus")){
                String repeat = line.getOptionValue("dnsstatus");
                int repeatInt = Integer.parseInt(repeat);
                for(int i =0; i<repeatInt; i++){
                    System.out.println( "dnsstatus");
                }
            }
            if( line.hasOption( "dnsblock" ) ) {
                String url = line.getOptionValue("dnsblock");
                dnsblock(url);
            }

        }catch(ParseException exp ) {
            System.out.println( "Unexpected exception:" + exp.getMessage() );
        }
    }

    private static void dnsblock(String blockUrl) {
        System.out.println(blockUrl);
    }

    public static void dnslist(){
//        System.out.println("dnslist() just get called");
    }
}
