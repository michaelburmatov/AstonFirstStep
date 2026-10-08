package App.States.Strategizing;

import App.Universal.ClassContainer;
import Architecture.GoF.Behavioral.StratageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandInputStratage implements StratageResult<List<ClassContainer>, Scanner> {
    @Override
    public List<ClassContainer> doStratage(Scanner context) {

        //---------- Просто пример ---------
        var input = context.nextLine();
        return new ArrayList<ClassContainer>();
    }
}
