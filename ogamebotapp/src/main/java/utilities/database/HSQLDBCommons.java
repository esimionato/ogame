package utilities.database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jarndt on 5/8/17.
 */
public class HSQLDBCommons {
    private static String defaultName = "ogniterDB";
    private static String dbName = "";
    private HSQLDB db = null;
    private HSQLDBCommons() throws IOException, SQLException {
        db = new HSQLDB(dbName);

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS BOTS(" +
                        "name VARCHAR(100)," +
                        "active INT," + //0 for no 1 for yes
                        "startDate BIGINT," +
                        "driverType VARCHAR(20)," +
                        "webDriverPath VARCHAR(100)," +
                        "proxy VARCHAR(50)," +
                        "windowWidth INT," +
                        "windowHeight INT," +
                        "windowPositionX INT," +
                        "windowPositionY INT," +
                        "PRIMARY KEY (name)" +
                ");"
        );

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS SERVER(" +
                        "name VARCHAR(50), " +
                        "number INT," +
                        "language VARCHAR(2)," +
                        "timezone VARCHAR(100)," +
                        "timezoneOffset VARCHAR(20)," +
                        "domain VARCHAR(250)," +
                        "version VARCHAR(10)," +
                        "speed INT," +
                        "speedFleet INT," +
                        "galaxies INT," +
                        "systems INT, " +
                        "acs INT, " +
                        "rapidFire INT, " +//0 for no 1 for yes
                        "defToTF INT, " +//0 for no 1 for yes
                        "debrisFactor DECIMAL, " +
                        "debrisFactorDef DECIMAL, " +
                        "repairFactor DECIMAL, " +
                        "newbieProtectionLimit INT, " +
                        "newbieProtectionHigh INT, " +
                        "topScore BIGINT, " +
                        "bonusFields INT, " +
                        "donutGalaxy INT, " +//0 for no 1 for yes
                        "donutSystem INT, " +//0 for no 1 for yes
                        "wfEnabled INT, " +//0 for no 1 for yes
                        "wfMinimumRessLost INT, " +
                        "wfMinimumLossPercentage INT," +
                        "wfBasicPercentageRepairable INT, " +
                        "globalDeuteriumSaveFactor INT, " +
                        "PRIMARY KEY (domain)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS HIGHSCORE_PLAYER( " +
                        "position INT, " +
                        "id INT, " +
                        "score BIGINT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (id,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS HIGHSCORE_ALLIANCE( " +
                        "position INT, " +
                        "id INT, " +
                        "score BIGINT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (id,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS PLAYERS( " +
                        "id INT, " +
                        "name VARCHAR(100), " +
                        "status VARCHAR(3), " +
                        "alliance INT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (id,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS ALLIANCES( " +
                        "id INT, " +
                        "name VARCHAR(100), " +
                        "tag VARCHAR(10), " +
                        "homepage VARCHAR(250), " +
                        "logo VARCHAR(250), " +
                        "open INT, " +
                        "player_id INT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (id,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS PLANETS( " +
                        "id BIGINT, " +
                        "player INT, " +
                        "name VARCHAR(100), " +
                        "coords VARCHAR(10), " +
                        "moon_id INT, " +
                        "moon_name VARCHAR(100), " +
                        "moon_size INT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (id,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS ESPIONAGE_REPORTS(" +
                        "coords VARCHAR(20), " +
                        "msgDate BIGINT, " +
                        "activityType VARCHAR(5), " +
                        "activityString VARCHAR(100), " +
                        "metal BIGINT, " +
                        "crystal BIGINT, " +
                        "deuterium BIGINT, " +
                        "lootPercent INT, " +
                        "counterEspionagePercent INT, " +
                        "fleets BIGINT, " +
                        "defence BIGINT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (coords, msgDate,universe_id)" +
                    ");");

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS COMBAT_REPORTS(" +
                        "coords VARCHAR(20), " +
                        "msgDate BIGINT, " +
                        "attacker VARCHAR(100), " +
                        "defender VARCHAR(100), " +
                        "lootedMetal BIGINT, " +
                        "lootedCrystal BIGINT, " +
                        "lootedDeuterium BIGINT, " +
                        "lootPercent INT, " +
                        "attackerLosses BIGINT, " +
                        "defenderLosses BIGINT, " +
                        "actuallyRepaired BIGINT, " +
                        "debrisField BIGINT, " +
                        "universe_id INT, " +
                        "PRIMARY KEY (coords, msgDate,universe_id)" +
                    ");");
    }

    static HSQLDBCommons instance;

    public static HSQLDBCommons getInstance() throws IOException, SQLException {
        if(instance == null)
            instance = new HSQLDBCommons();
        return instance;
    }

    public static void setDbName(int dbNameNumber){
        dbName=defaultName+dbNameNumber;
    }
    public static void setDbName(String dbName){
        dbName=defaultName+"_"+dbName;
    }

    public static List<Map<String, Object>> executeQuery(String query) throws IOException, SQLException {
        return getInstance().db.executeQuery(query);
    }

}