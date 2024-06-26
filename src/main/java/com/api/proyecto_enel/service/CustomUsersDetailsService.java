package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.repository.IAdminRepository;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUsersDetailsService implements UserDetailsService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByRutAdmin(rut);
        Optional<Cliente> clienteOptional = clienteRepository.findByRutCliente(rut);
        Optional<Empresa> empresaOptional = empresaRepository.findByRutEmpresa(rut);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return buildUserDetails(admin.getRut_admin(), admin.getClave_admin(), "ADMIN");
        } else if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return buildUserDetails(cliente.getRut_cliente(), cliente.getClave_cliente(), "CLIENTE");
        } else if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            return buildUserDetails(empresa.getRut_empresa(), empresa.getClave_empresa(), "EMPRESA");
        } else {
            throw new UsernameNotFoundException("No se encontró ningún usuario con el RUT: " + rut);
        }
    }

    private UserDetails buildUserDetails(String rut, String password, String role) {
        return User.withUsername(rut)
                .password(password)
                .roles(role)
                .build();
    }
}
