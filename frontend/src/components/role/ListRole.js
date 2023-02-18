import React, { Component } from 'react'
import RoleService from '../../services/role.service';
import { Link } from "react-router-dom";
import Message from '../../common/Message';
import { Modal } from 'react-responsive-modal';
import 'react-responsive-modal/styles.css';

let record = {"roleid":"","name":"","creation_date":"","create_user":"","update_date":"","update_user":"","status":""};
let tittle = "Roles List";
let model = "Role";
let map = "/roles/";
 
class ListRole extends Component {
    constructor(props) {
        super(props)

        this.state = {
            records: []
        }                            
    }

    

    deleteRecord(id){
        if (window.confirm("Are you sure to delete this record?"))
        { 
            RoleService.deleteRecord(id).then( res => {
                this.setState({records: this.state.records.filter(record => record.roleid !== id)});
            });                        
            Message.Deleted();
        }

        
    }
    
    componentDidMount(){
        RoleService.getRecords().then((res) => {            
            this.setState({ records: res.data.data});
        });
    }
    
    state={
        openModal : false
    }

    open(id)
    {
        RoleService.getRecordById(id).then( (res) =>{         
                    
            if(res.data.status === 200 )
            {
                record = res.data.data;                  
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
                                    <th> ID</th>
                                    <th> Name</th>                                    
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.records.map(
                                        record => 
                                        <tr key = {record.roleid}>
                                             <td> {record.name}</td>
                                             <td>
                                                 <Link to={map+record.roleid }><button style={{marginLeft: "10px"}} className="btn btn-success">Edit</button></Link>                                                 
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteRecord(record.roleid)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={() =>this.open(record.roleid)} className="btn btn-info">View </button>                                                
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
                                        <th scope="row">ID</th>
                                        <td>{record.roleid}</td>                                   
                                    </tr>
                                    <tr>
                                        <th scope="row">Name</th>
                                        <td>{record.name}</td>                                   
                                    </tr>
                                    <tr>
                                        <th scope="row">Creation Date</th>
                                        <td>{record.creation_date}</td>                                    
                                    </tr>
                                    <tr>
                                        <th scope="row">Status</th>
                                        <td>{record.status}</td>                                    
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

export default ListRole
