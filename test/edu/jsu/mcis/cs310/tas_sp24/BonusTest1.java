package edu.jsu.mcis.cs310.tas_sp24;

import edu.jsu.mcis.cs310.tas_sp24.dao.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BonusTest1 {

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");

    }

    
    @Test
    public void testFindShiftByID() {

        ShiftDAO shiftDAO = daoFactory.getShiftDAO();

        /* Retrieve Shift Rulesets from Database */
        
        Shift s1 = shiftDAO.find(4);

        /* Compare to Expected Values */
       
        assertEquals("Shift 3: 22:30 - 07:00 (510 minutes); Lunch: 02:30 - 03:00 (30 minutes)", s1.toString());

    }
    
}
