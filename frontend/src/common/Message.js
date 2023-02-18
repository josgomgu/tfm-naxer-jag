import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Success = () => {
    toast.configure();
   return toast.success("Record Saved !", {
        position: toast.POSITION.TOP_RIGHT,
        autoClose: 3000
      });
  };

const Deleted = () => {
    toast.configure();
   return toast.success("Record Deleted !", {
        position: toast.POSITION.TOP_RIGHT,
        autoClose: 3000
      });
  };  

const Message = {
    Success,
    Deleted
  };

export default Message;
