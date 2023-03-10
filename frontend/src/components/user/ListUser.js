import React, { Component } from 'react'
import UserService from '../../services/user.service';
import { Link } from "react-router-dom";
import Message from '../../common/Message';
import { Modal } from 'react-responsive-modal';
import 'react-responsive-modal/styles.css';

let user2 = {"userid":"","login":"","password":"","confirmPwd":"","name":"","last_name":"","last_login":"","creation_date":"","create_user":"","update_date":"","update_user":"","status":""};
let tittle = "Users List";
let model = "User";
let map = "/users/";
 
class ListUser extends Component {
    constructor(props) {
        super(props)

        this.state = {
                users: []
        }  
                          
        
    }

    

    deleteRecord(id){
        if (window.confirm("Are you sure to delete this record?"))
        { 
            UserService.deleteUser(id).then( res => {
                this.setState({users: this.state.users.filter(user => user.userid !== id)});
            });                        
            Message.Deleted();
        }

        
    }
    
    componentDidMount(){
        UserService.getUsers().then((res) => {            
            this.setState({ users: res.data.data});
        });
    }

    
    state={
        openModal : false
    }

    
    open(id)
    {
        UserService.getUserById(id).then( (res) =>{         
                    
            if(res.data.status === 200 )
            {
                user2 = res.data.data;  
                
                this.setState({openModal : true}) 
            } 
        }
            );      
           
    }    

    onCloseModal = ()=>{
        this.setState({openModal : false})
    }
    
    render() {
        return (
            <div>
                 <h2 className="text-center">{tittle}</h2>
                 <div className = "row">
                    
                    <Link to={map+"_add" }><button className="btn btn-primary"> Add {model}</button></Link>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Login</th>
                                    <th> Name</th>
                                    <th> LastName</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.users.map(
                                        user => 
                                        <tr key = {user.userid}>
                                             <td> {user.login}</td>
                                             <td> {user.name} </td>   
                                             <td> {user.last_name}</td>
                                             <td>
                                                 <Link to={map+user.userid }><button style={{marginLeft: "10px"}} className="btn btn-success">Edit</button></Link>                                                 
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteRecord(user.userid)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={() =>this.open(user.userid)} className="btn btn-info">View </button>                                                
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                        <Modal open={this.state.openModal} onClose={this.onCloseModal}>
                            <br></br>
                            <center>
                            <h3>View Record</h3>
                            <br></br>
                            
                            <table className="table">
                                
                                <tbody>
                                    <tr>
                                        <th scope="row">Login</th>
                                        <td>{user2.login}</td>                                   
                                    </tr>
                                    <tr>
                                        <th scope="row">Name</th>
                                        <td>{user2.name}</td>                                   
                                    </tr>
                                    <tr>
                                        <th scope="row">LastName</th>
                                        <td>{user2.last_name}</td>                                    
                                    </tr>
                                    <tr>
                                        <th scope="row">Last Login</th>
                                        <td>{user2.last_login}</td>                                    
                                    </tr>
                                    <tr>
                                        <th scope="row">Creation Date</th>
                                        <td>{user2.creation_date}</td>                                    
                                    </tr>
                                    <tr>
                                        <th scope="row">Status</th>
                                        <td>{user2.status}</td>                                    
                                    </tr>                                    
                                </tbody>
                            </table>
                            </center>
                        </Modal>                 
                 </div>

            </div>
        )
    }
}

export default ListUser
