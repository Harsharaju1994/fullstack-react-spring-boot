import React,{useEffect, useState} from 'react'
import { deletEmployee, listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {

    const [employees, setEmployees]=useState([])
    const navigator = useNavigate();

    useEffect(()=>{
        getAllEmployees();
    },[])
    function getAllEmployees(){
        listEmployees().then((Response)=>{
            setEmployees(Response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewEmployee(){
        navigator('add-employee')
    }

    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }

    function removeEmployee(id){
        console.log(id)
        deletEmployee(id).then((res)=>{
            getAllEmployees()
        }).catch(error => {
            console.log(error);
        })
    }
    // const dummyData =[
    //     {
    //         "id":1,
    //         "firstName":"RajaGopal Reddy",
    //         "lastName":"Yenumula",
    //         "email":"raj.info97@gmail.com"
    //     },
    //     {
    //         "id":2,
    //         "firstName":"HarshaVardan Reddy",
    //         "lastName":"Yenumula",
    //         "email":"harsha.info94@gmail.com"
    //     },
    //     {
    //         "id":3,
    //         "firstName":"ChandraSekar Reddy",
    //         "lastName":"Gayam",
    //         "email":"sekar.info96@gmail.com"
    //     }
    // ]
  return (
    <>
        <div className='container'>

        <h2 className='text-center'>List os Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email Id</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(employee =>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>
                                <button className='btn btn-info' onClick={()=>updateEmployee(employee.id)}
                                    style={{marginLeft:'20px'}}
                                >Update</button>
                                <button className='btn btn-danger' onClick={()=>removeEmployee(employee.id)}
                                    style={{marginLeft:'20px'}}
                                >Delete</button>
                            </td>
                        </tr>
                    )
                }
                
            </tbody>

        </table>
        </div>
    </>
  )
}

export default ListEmployeeComponent