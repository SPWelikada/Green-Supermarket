package serviceImpl;

import dto.AdminDTO;
import dto.LoginDTO;

import repoImpl.AdminRepositoryImpl;


public class AdminServiceImpl  {
    AdminRepositoryImpl  adminRepository = new AdminRepositoryImpl();

    public AdminDTO addAdmin(AdminDTO adminDTO) throws Exception {
        return adminRepository.save(adminDTO);
    }

    public LoginDTO adminLogin(LoginDTO loginDTO) throws Exception {
        return adminRepository.getAdminByUserNameAndPassword(loginDTO);
    }
}
