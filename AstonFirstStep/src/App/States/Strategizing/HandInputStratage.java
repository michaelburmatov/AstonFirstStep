package App.States.Strategizing;

import Architecture.GoF.Behavioral.StratageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandInputStratage implements StratageResult<List<?>, Scanner> {
    @Override
    public List<?> doStratage(Scanner context) {

        //---------- Просто пример ---------
        var input = context.nextLine();
        return new ArrayList<String>();
    }
}
