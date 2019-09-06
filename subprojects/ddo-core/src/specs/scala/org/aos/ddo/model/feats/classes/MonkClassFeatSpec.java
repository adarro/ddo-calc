package org.aos.ddo.model.feats.classes;

import enumeratum.Enum;
import enumeratum.EnumEntry;
import org.aos.ddo.model.classes.CharacterClass;
import org.aos.ddo.model.classes.CharacterClass$;
import org.aos.ddo.model.feats.ClassDisplayHelper;
import org.concordion.api.FullOGNL;
import org.junit.runner.RunWith;
import org.concordion.integration.junit4.ConcordionRunner;

import java.util.List;

@FullOGNL
@RunWith(ConcordionRunner.class)
public class MonkClassFeatSpec extends MonkJavaHelper {
    // This wrapper class is no longer needed.
//    MonkJavaHelper helper = new MonkJavaHelper() {
//        @Override
//        public CharacterClass cClass() {
//            return super.cClass();
//        }
//    };
//    public List<String> grantedFeatsByLevel(Integer level) {
//        return  helper.grantedFeatsByLevel(level);
//    }
//
//    public List<String> Simple() {
//        return  java.util.Arrays.asList("Simple"); //helper.grantedFeatsByLevel(level);
//    }
//
//    public List<String> filterByClassBonusFeat() {
//        return  java.util.Arrays.asList("Simple"); //helper.grantedFeatsByLevel(level);
//    }

}
