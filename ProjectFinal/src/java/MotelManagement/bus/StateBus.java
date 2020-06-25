package MotelManagement.bus;

import MotelManagement.dao.StateDao;
import MotelManagement.dto.State;

import java.util.List;

public class StateBus {
    private StateDao stateDao;

    public StateBus() {
        stateDao = new StateDao();
    }
    
    public String nextId() {
        return stateDao.nextId();
    }
    public boolean insert(State state) {
        state.setId(nextId());
        return stateDao.insert(state);
    }
    
    public boolean update(State state) {
        return stateDao.update(state);
    }
    
    public boolean delete(State state) {
        return stateDao.delete(state);
    }
    
    public List<State> getAll() {
        return stateDao.selectAll();
    }
}
