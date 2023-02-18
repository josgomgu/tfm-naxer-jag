import React, { useState,useEffect } from "react";
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as Yup from 'yup'
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import UserService from '../../services/user.service';
import Message from '../../common/Message';

export default function ConfirmPassword() {


  const formSchema = Yup.object().shape({
    password: Yup.string()
      .required('Password is mandatory')
      .min(3, 'Password must be at 3 char long'),
    confirmPwd: Yup.string()
      .required('Password is mandatory')
      .oneOf([Yup.ref('password')], 'Passwords does not match'),
    login: Yup.string()
      .required('Login is mandatory')
      .min(5, 'Login must be at 5 char long'),
    name: Yup.string()
      .required('Name is mandatory')
      .min(5, 'Name must be at 5 char long'),    
    last_name: Yup.string()      
  })

  const formOptions = { resolver: yupResolver(formSchema) }
  const { register, handleSubmit, formState,reset } = useForm(formOptions)
  const { errors } = formState
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState(null);

  const  params  = useParams();

  let tittle = '';
  let readonly = false;
  let model = 'users';
  let modelName = 'User';  

  let navigate = useNavigate();


  const [trx] = useState(params.id);

  if(trx ==='_add')

    tittle = 'Add '+modelName;
  else
  {
    tittle = 'Update '+modelName;
    readonly = true;
  }   

useEffect(() => {
   
    
    if(trx !=='_add')
    {
        
        UserService.getUserById(trx).then( (res) =>{         
        
        if(res=== null){
            navigate("/login");
            window.location.reload();
        }
        
        if(res.data.status === 200 )
        {
            let user2 = res.data.data;                  
            setUser({"userid":user2.userid,
            "login":user2.login,
            "password":user2.password,
            "confirmPwd":user2.password,
            "name":user2.name,
            "last_name":user2.last_name,
            "last_login":user2.last_login,
            "creation_date":user2.creation_date,
            "create_user":user2.create_user,
            "update_date":user2.update_date,
            "update_user":user2.update_user,
            "status":user2.status})
        } 
        else
        {
            navigate("/"+model);
        }     
        });         
    } 
}, [trx,model,navigate]);

  // effect runs when user state is updated
  useEffect(() => {
        // reset form with user data
        reset(user);
    }, [user,reset]);


  
  const onCancel = (e) => {
    navigate("/"+model);
  };

  function onSubmit(data) {
    setMessage('');
    setLoading(true);

    
    if(trx ==='_add')
    {
        UserService.addUser(data).then(response =>{

            setMessage('');
            if(JSON.stringify(response.data.status) !== "200"){
                setMessage(JSON.stringify(response.data.message));             
                setLoading(false);
            }
                
            else {
                Message.Success();
                navigate("/"+model);
                //window.location.reload();                
            }
        });
    } 
    else
    {
        UserService.updateUser(data).then(response =>{

            setMessage('');
            if(JSON.stringify(response.data.status) !== "200"){
                setMessage(JSON.stringify(response.data.message));             
                setLoading(false);
            }
                
            else {
              Message.Success();
              navigate("/"+model);
                //window.location.reload();                
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
                <label>Login</label>
                <input  type="text" 
                        name="login" 
                        {...register('login')}
                        readOnly={readonly} 
                        className={`form-control ${errors.login ? 'is-invalid' : ''}`}                                                         
                        />     
                <div className="invalid-feedback">{errors.login?.message}</div>                           
            </div> 

            <div className="form-group">
                <label>Password</label>
                <input
                    name="password"
                    type="password"
                    {...register('password')}
                    readOnly={readonly}
                    className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                />
                <div className="invalid-feedback">{errors.password?.message}</div>
            </div>
           
            <div className="form-group" >
                <label>Confirm Password</label>
                <input
                    name="confirmPwd"
                    type="password"
                    {...register('confirmPwd')}
                    readOnly={readonly}
                    className={`form-control ${errors.confirmPwd ? 'is-invalid' : ''}`}
                />
                <div className="invalid-feedback">{errors.confirmPwd?.message}</div>
            </div>

            <div className = "form-group">
                <label htmlFor="name">Name</label>
                <input  type="text" 
                        name="name" 
                        {...register('name')}
                        className={`form-control ${errors.name ? 'is-invalid' : ''}`}                                        
                        />     
                <div className="invalid-feedback">{errors.name?.message}</div>                           
            </div>

            <div className = "form-group">
                <label htmlFor="last_name">Last Name</label>
                <input  type="text" 
                        name="last_name" 
                        {...register('last_name')}
                 className={`form-control ${errors.last_name ? 'is-invalid' : ''}`}                                        
                        />     
                <div className="invalid-feedback">{errors.last_name?.message}</div>                           
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