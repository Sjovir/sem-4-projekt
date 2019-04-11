package org.grp2.gms.dao;

import org.grp2.gms.common.GreenhouseDTO;
import org.grp2.gms.domain.Greenhouse;

import java.util.Map;

public interface IGreenhouseDAO {

    /**
     * Returns the latest recorded data on the greenhouse with the given ID
     * @param id of the greenhouse
     * @return Map of all the data values
     */
    GreenhouseDTO getLatestGreenhouseData(int id);

}
