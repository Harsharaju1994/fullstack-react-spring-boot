package org.in60mins.service;

import lombok.AllArgsConstructor;
import org.in60mins.dto.EmployeeDto;
import org.in60mins.entity.Employee;
import org.in60mins.exception.ResourceNotFoundException;
import org.in60mins.mapper.EmployeeMapper;
import org.in60mins.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.maptoEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Teacher does not exists with given id : " +employeeId));
        return EmployeeMapper.maptoEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employee> teacher = employeeRepository.findAll();
        return teacher
                .stream()
                .map(
                        (teah)->EmployeeMapper.maptoEmployeeDto(teah))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto editById(Long employeeid,
                                EmployeeDto employeeDto)
    {
        Employee employee = employeeRepository.findById(employeeid)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Teacher does not exists with given id : " +employeeid)
                );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.maptoEmployeeDto(updatedEmployee);

    }

    @Override
    public void deleteById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Teacher does not exists with given id : " +employeeId)
        );
        employeeRepository.deleteById(employeeId);

    }
}
