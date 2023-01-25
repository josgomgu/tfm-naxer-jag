import React, { useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import UserService from '../../services/user.service';
import { useParams } from 'react-router-dom';


const required = (value) => {
  
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};



const PersistUser = () => {

  const  params  = useParams();

  let tittle = '';
  let displayPass = 'block';
  let model = 'users';
  let modelName = 'User';

  let navigate = useNavigate();

  const form = useRef();
  const checkBtn = useRef();

  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [name, setName] = useState("");
  const [last_name, setLastName] = useState("");
  const [trx] = useState(params.id);
  

  if(trx ==='_add')

    tittle = 'Add '+modelName;
  else
  {
    tittle = 'Update '+modelName;
    displayPass = 'none';
    UserService.getUserById(trx).then( (res) =>{         
      
      if(res=== null){
        navigate("/login");
        window.location.reload();
      }
      console.log(res.data.status)
      if(res.data.status === 200 )
      {
          let user = res.data.data;
            console.log(JSON.stringify(user));
            setLogin(user.login);        
            setPassword(user.password);
            setName(user.name);
            setLastName(user.last_name);
      } 
      else
      {
        navigate("/"+model);
      }     
    }); 
    
    
  }




  const onChangeLogin = (e) => {
    const login = e.target.value;
    setLogin(login);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const onChangeName = (e) => {
    const name = e.target.value;
    setName(name);
  };

  const onChangeLastName = (e) => {
    const last_name = e.target.value;
    setLastName(last_name);
  };

  const onCancel = (e) => {
    navigate("/"+model);
  };

  
  const handlePersist = (e) => {
    e.preventDefault();

    
    setMessage('');
    setLoading(true);

    form.current.validateAll();
       
    if (checkBtn.current.context._errors.length === 0) {
      let user = {name: name, last_name: last_name, login: login, password: password};
      UserService.addUser(user).then(response =>{

            setMessage('');
            if(JSON.stringify(response.data.status) !== "200"){
                setMessage(JSON.stringify(response.data.message));             
                setLoading(false);
            }
                
            else {
                navigate("/"+model);
                window.location.reload();                
            }
        })
    }
    else
        setLoading(false);
  };

  return (
    <div className = "container" >
      <div className = "row">
        <div className = "card col-md-6 offset-md-3 offset-md-3">
        <h3 className="text-center">{tittle}</h3>
        <div className = "card-body"></div>

            <Form onSubmit={handlePersist} ref={form}>
                <div className = "form-group">
                    <label htmlFor="login">Login</label>
                    <Input  type="text" 
                            name="login" 
                            className="form-control" 
                            value={login}
                            onChange={onChangeLogin}
                            validations={[required]}
                            />
                </div>  
                            
                    <div className="form-group" style={{ display: displayPass }}>
                        <label htmlFor="password">Password</label>
                        <Input
                        type="password"
                        className="form-control"
                        name="password"
                        value={password}
                        onChange={onChangePassword}
                        validations={[required]}
                        
                        />
                    </div>

                    <div className="form-group" style={{ display: displayPass }}>
                        <label htmlFor="repassword">Re-Password</label>
                        <Input
                        type="password"
                        className="form-control"
                        name="repassword"
                        value={password}
                        onChange={onChangePassword}
                        validations={[required]}
                        
                        />
                    </div>

                    <div className = "form-group">
                        <label htmlFor="name">Name</label>
                        <Input  type="text" 
                                name="name" 
                                className="form-control" 
                                value={name}
                                onChange={onChangeName}
                                validations={[required]}
                                />
                    </div>

                    <div className = "form-group">
                        <label htmlFor="name">LastName</label>
                        <Input  type="text" 
                                name="last_name" 
                                className="form-control" 
                                value={last_name}
                                onChange={onChangeLastName}
                                validations={[required]}
                                />
                    </div>

                <div className="form-group">
                    <button className="btn btn-primary" disabled={loading} >
                    {loading && (
                        <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Save</span>
                    </button>
                    <button className="btn btn-danger" style={{marginLeft: "10px"}} onClick={onCancel}>Cancel</button>
                </div>

                {message && (
                    <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                    </div>
                )}
                <CheckButton style={{ display: "none" }} ref={checkBtn} />
            </Form>
        </div>    
      </div>
    </div>
  );
};

export default PersistUser;
