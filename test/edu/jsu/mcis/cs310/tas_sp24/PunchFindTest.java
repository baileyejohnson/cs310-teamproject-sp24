package edu.jsu.mcis.cs310.tas_sp24;

import edu.jsu.mcis.cs310.tas_sp24.dao.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PunchFindTest {

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");

    }

    @Test
    public void testFindPunches1() {

        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Retrieve Punches from Database */
        
        Punch p1 = punchDAO.find(3433);
        Punch p2 = punchDAO.find(3325);
        Punch p3 = punchDAO.find(1963);

        /* Compare to Expected Values */
        
        assertEquals("#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07", p1.printOriginal());
        assertEquals("#DFD9BB5C CLOCK IN: TUE 09/04/2018 08:00:00", p2.printOriginal());
        assertEquals("#99F0C0FA CLOCK IN: SAT 08/18/2018 06:00:00", p3.printOriginal());

    }

    @Test
    public void testFindPunches2() {

        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Retrieve Punches from Database */

        Punch p4 = punchDAO.find(5702);
        Punch p5 = punchDAO.find(4976);
        Punch p6 = punchDAO.find(2193);

        /* Compare to Expected Values */

        assertEquals("#0FFA272B CLOCK OUT: MON 09/24/2018 17:30:04", p4.printOriginal());
        assertEquals("#FCE87D9F CLOCK OUT: TUE 09/18/2018 17:34:00", p5.printOriginal());
        assertEquals("#FCE87D9F CLOCK OUT: MON 08/20/2018 17:30:00", p6.printOriginal());

    }
    
    @Test
    public void testFindPunches3() {

        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Retrieve Punches from Database */

        Punch p7 = punchDAO.find(954);
        Punch p8 = punchDAO.find(258);
        Punch p9 = punchDAO.find(717);

        /* Compare to Expected Values */

        assertEquals("#618072EA TIME OUT: FRI 08/10/2018 00:12:35", p7.printOriginal());
        assertEquals("#0886BF12 TIME OUT: THU 08/02/2018 06:06:38", p8.printOriginal());
        assertEquals("#67637925 TIME OUT: TUE 08/07/2018 23:12:34", p9.printOriginal());

    }
    
    @Test
    public void testFindPunches4() {

        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Retrieve Punches from Database */

        Punch p10 = punchDAO.find(2067);
        Punch p11 = punchDAO.find(446);
        Punch p12 = punchDAO.find(3872);

        /* Compare to Expected Values */

        assertEquals("#408B195F CLOCK IN: TUE 08/21/2018 07:01:58", p10.printOriginal());
        assertEquals("#9E06A774 CLOCK OUT: FRI 08/03/2018 15:31:07", p11.printOriginal());
        assertEquals("#DF19620C TIME OUT: SAT 09/08/2018 22:44:40", p12.printOriginal());

    }
    
    @Test
    public void testFindPunches5() {

        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Retrieve Punches from Database */

        Punch p13 = punchDAO.find(1092);
        Punch p14 = punchDAO.find(1658);
        Punch p15 = punchDAO.find(833);

        /* Compare to Expected Values */

        assertEquals("#E06BE060 CLOCK IN: SAT 08/11/2018 05:57:46", p13.printOriginal());
        assertEquals("#CEA28723 CLOCK OUT: THU 08/16/2018 16:32:24", p14.printOriginal());
        assertEquals("#FF591F68 TIME OUT: WED 08/08/2018 23:12:34", p15.printOriginal());

    }    
    
}
