package cleancode.studycafe.my.model.pass;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;

public interface StudyCafePass {

    StudyCafePassType getPassType();

    Charge getCharge();

}
