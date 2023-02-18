import React, { useState,useEffect } from "react";
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as Yup from 'yup'
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import RoleService from '../../services/role.service';
import Message from '../../common/Message';

export default function PersistRole() {


  const formSchema = Yup.object().shape({
    name: Yup.string()
      .required('Name is mandatory')
      .min(5, 'Name must be at 5 char long')
  })

  const formOptions = { resolver: yupResolver(formSchema) }
  const { register, handleSubmit, formState,reset } = useForm(formOptions)
  const { errors } = formState
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [record, setRecord] = useState(null);

  const  params  = useParams();

  let tittle = '';
  let model = 'roles';
  let modelName = 'Role';  

  let navigate = useNavigate();


  const [trx] = useState(params.id);

  if(trx ==='_add')

    tittle = 'Add '+modelName;
  else
  {
    tittle = 'Update '+modelName;    
  }   

useEffect(() => {
   
    
    if(trx !=='_add')
    {
        
        RoleService.getRecordById(trx).then( (res) =>{         
        
        if(res=== null){
            //navigate("/login");
            //window.location.reload();
        }
        
        if(res.data.status === 200 )
        {
            let record2 = res.data.data;                  
            setRecord({"roleid":record2.roleid,
            "name":record2.name,
            "creation_date":record2.creation_date,
            "create_user":record2.create_user,
            "update_date":record2.update_date,
            "update_user":record2.update_user,
            "status":record2.status})
        } 
        else
        {
            navigate("/"+model);
        }     
        });         
    } 
}, [trx,model,navigate]);

  // effect runs when reocord state is updated
  useEffect(() => {
        // reset form 
        reset(record);
    }, [record,reset]);


  
  const onCancel = (e) => {
    navigate("/"+model);
  };

  function onSubmit(data) {
    setMessage('');
    setLoading(true);

    
    if(trx ==='_add')
    {
        RoleService.addRecord(data).then(response =>{

            setMessage('');
            if(JSON.stringify(response.data.status) !== "200"){
                setMessage(JSON.stringify(response.data.message));             
                setLoading(false);
            }
                
            else {
                Message.Success();
                navigate("/"+model);                
            }
        });
    } 
    else
    {
        RoleService.updateRecord(data).then(response =>{

            setMessage('');
            if(JSON.stringify(response.data.status) !== "200"){
                setMessage(JSON.stringify(response.data.message));             
                setLoading(false);
            }
                
            else {
              Message.Success();
              navigate("/"+model);                
            }
        })
    }    
    
    return false
  }
  return (
    <div className = "container" >
      <div className = "row">
        <div className = "card col-md-6 offset-md-3 offset-md-3">
        <h3 className="text-center">{tittle}</h3>
        <div className = "card-body"></div>

        <form onSubmit={handleSubmit(onSubmit)}>           
                
            <div className = "form-group">
                <label htmlFor="name">Name</label>
                <input  type="text" 
                        name="name" 
                        {...register('name')}
                        className={`form-control ${errors.name ? 'is-invalid' : ''}`}                                        
                        />     
                <div className="invalid-feedback">{errors.name?.message}</div>                           
            </div>
            
            <div className="mt-3">
                <button className="btn btn-primary" disabled={loading} type="submit">
                    {loading && (
                        <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Save</span>
                </button>
                <button className="btn btn-danger" style={{marginLeft: "10px"}} onClick={onCancel}>Cancel</button>
            </div>
            <div className="mt-3">
                {message && (
                    <div className="form-group text-center">
                        <div className="alert alert-danger" role="alert">
                            {message}
                        </div>
                    </div>
                )}
                
            </div>
            
        </form>
        </div>    
      </div>
    </div>
  )
}