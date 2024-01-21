package serviceImpl;

import entity.HelpDesk;

import repoImpl.HelpDeskRepositoryImpl;

import java.util.List;


public class HelpDeskServiceImpl  {
    private HelpDeskRepositoryImpl helpDeskRepository = new HelpDeskRepositoryImpl();


    public void addHelpDesk(HelpDesk helpDesk) {
        helpDeskRepository.addHelpDesk(helpDesk);
    }


    public void updateHelpDesk(HelpDesk helpDesk) {
        helpDeskRepository.updateHelpDesk(helpDesk);
    }


    public void deleteHelpDesk(int helpDeskId) {
        helpDeskRepository.deleteHelpDesk(helpDeskId);
    }


    public HelpDesk getHelpDeskById(int helpDeskId) {
        return helpDeskRepository.getHelpDeskById(helpDeskId);
    }


    public List<HelpDesk> getAllHelpDesk() {
        return helpDeskRepository.getAllHelpDesk();
    }
}
