import React, { Component } from 'react'
import UserService from '../../services/user.service';
import { Link } from "react-router-dom";
 
class ListUser extends Component {
    constructor(props) {
        super(props)

        this.state = {
                users: []
        }
        /*
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
        */
    }

    deleteUser(id){
        UserService.deleteUser(id).then( res => {
            this.setState({users: this.state.users.filter(user => user.userid !== id)});
        });
    }
    viewUser(id){
        this.props.history.push(`/view-employee/${id}`);
    }
    editUser(id){
        this.props.history.push(`/users/${id}`);
    }

    componentDidMount(){
        UserService.getUsers().then((res) => {            
            this.setState({ users: res.data.data});
        });
    }

    
    render() {
        return (
            <div>
                 <h2 className="text-center">Users List</h2>
                 <div className = "row">
                    
                    <Link to={"/users/_add" }><button className="btn btn-primary"> Add User</button></Link>
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
                                                 <Link to={"/users/"+user.userid }><button style={{marginLeft: "10px"}} className="btn btn-success">Edit</button></Link>                                                 
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteUser(user.userid)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewUser(user.userid)} className="btn btn-info">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListUser
