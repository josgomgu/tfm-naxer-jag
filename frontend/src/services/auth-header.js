export default function authHeader() {
  const token = JSON.parse(localStorage.getItem('token'));

  if (token) {
    return { "Content-Type": 'application/json',
              "Access-Control-Allow-Origin": '*',
              "Access-Control-Allow-Methods": 'DELETE, POST, GET, OPTIONS',
              "Authorization": token }; 
  } else {
    return {};
  }
}