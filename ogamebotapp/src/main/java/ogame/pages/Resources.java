package ogame.pages;

import bot.Bot;
import ogame.objects.game.Resource;
import org.jsoup.nodes.Document;

/**
 * Created by jarndt on 5/8/17.
 */
public class Resources implements OgamePage{
    public static void main(String[] args) {
        for (int i = 0; i <= 31; i++)
            System.out.println(i+": "+getStorageCapacityLevel(i));
    }

    public static final String ID = "ref";
    public static final String RESOURCES = "Resources";
    public static final String
            METAL_MINE              = "Metal Mine",
            CRYSTAL_MINE            = "Crystal Mine",
            DUETERIUM_SYNTHESIZER   = "Deuterium Synthesizer",
            SOLAR_PLANET            = "Solar Plant",
            FUSION_REACTOR          = "Fusion Reactor",
            METAL_STORAGE           = "Metal Storage",
            CRYSTAL_STORAGE         = "Crystal Storage",
            DUETERIUM_TANK          = "Deuterium Tank";

    public static String[] names = {
            METAL_MINE, CRYSTAL_MINE, DUETERIUM_SYNTHESIZER,
            SOLAR_PLANET, FUSION_REACTOR,
            METAL_STORAGE, CRYSTAL_STORAGE,DUETERIUM_TANK
    };

    public static long getStorageCapacityLevel(int level){
        return 5000*(int)(2.5*Math.pow(Math.E,20*level/33.));//-(level<2 || (level>= 5 && level <11)? 5000:0);
    }

    public static Resource[] baseCosts;

    @Override
    public String getPageName() {
        return RESOURCES;
    }

    @Override
    public String getXPathSelector() {
        return "//*[@id='menuTable']/li[2]/a/span";
    }

    @Override
    public String getCssSelector() {
        return "#menuTable > li:nth-child(2) > a > span";
    }

    @Override
    public String uniqueXPath() {
        return "//*[@id='slot01']/a";
    }

    @Override
    public void parsePage(Bot b, Document document) {
        PageController.parseGenericBuildings(document,b);

        //TODO Buildings being built

        //TODO parse other planet's resources
    }
}
