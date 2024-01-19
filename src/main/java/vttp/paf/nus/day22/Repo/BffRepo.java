package vttp.paf.nus.day22.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import vttp.paf.nus.day22.Utils;
import vttp.paf.nus.day22.model.Bff;

public class BffRepo {
    
    @Autowired
    private JdbcTemplate template;

    public Bff selectBFFbyEmail(String email){
        
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BFF_BY_EMAIL,email);

        while (rs.next()){
            return Utils.toBff(rs);
        }
        return null; //return null if nothing is found
    }
    // is it me or is thhis format like nicer?

    // no choice this one will be like this.
    public boolean InsertBFF(Bff bff) {
        return template.update(
            Queries.SQL_INSERT_BFF,
            bff.getEmail(),
            bff.getName(),
            bff.getDob(),
            bff.getPhone(),
            bff.getComments(),
            bff.getLastUpdate()
        ) >= 1;
        }
}
