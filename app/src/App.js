import React from 'react';
import axios from 'axios';

//res -> response
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      id: 0,
      Fullname: '',
      Email: '',
      Age: 0,
      Password: ''

    }
  }
  componentDidMount() {
    axios.get("http://localhost:8080/UserInfo/")
      .then((res) => {
        this.setState({
          users: res.data,
          id: 0,
          fullname: '',
          email: '',
          age: 0,
          password: ''
        })
      })
  }

  submit(evenet, id) {
    console.log(id)
    evenet.preventDefault();
    if (id === 0) {
      axios.post("http://localhost:8080/UserInfo/", {
        fullname: this.state.fullname,
        email: this.state.email,
        age: this.state.age,
        password: this.state.password
      }).then(() => {
        this.componentDidMount();
      })
    } else {
      axios.put("http://localhost:8080/UserInfo/", {
        id: id,
        fullname: this.state.fullname,
        email: this.state.email,
        age: this.state.age,
        password: this.state.password
      }).then(() => {
        this.componentDidMount();
      })
    }
  }
  delete(id) {
    axios.delete("http://localhost:8080/UserInfo/" + id)
      .then(() => {
        this.componentDidMount();
      })
  }

  edit(id) {
    axios.get("http://localhost:8080/UserInfo/" + id)
      .then((res) => {
        this.setState({
          id: res.data.id,
          fullname: res.data.fullname,
          email: res.data.email,
          age: res.data.age,
          password: res.data.password
        });
      })
  }

  render() {
    return (
      <div id="main" >


        <form id="add-book" onSubmit={(e) => this.submit(e, this.state.id)}>

          <div className="field">
            <label htmlFor="autocomplete-input">Enter Fullname</label>
            <input value={this.state.fullname} onChange={(e) => this.setState({ fullname: e.target.value })} type="text" id="autocomplete-input" className="autocomplete" />
          </div>

          <div className="field">
            <label htmlFor="autocomplete-input">Enter Email</label>
            <input value={this.state.email} onChange={(e) => this.setState({ email: e.target.value })} type="email" id="autocomplete-input" className="autocomplete" />
          </div>

          <div className="field">
            <label htmlFor="autocomplete-input">Enter Age</label>
            <input value={this.state.age} onChange={(e) => this.setState({ age: e.target.value })} type="number" id="autocomplete-input" className="autocomplete" />
          </div>

          <div className="field">
            <label htmlFor="autocomplete-input">Enter Password</label>
            <input value={this.state.password} onChange={(e) => this.setState({ password: e.target.value })} type="password" id="autocomplete-input" className="autocomplete" />
          </div>

          <button >Submit</button>

        </form>


        <div >
          <h1>All Current Members</h1>
          <table>
            <thead>
              <tr >
                <th >Fullname</th>
                <th >Email</th>
                <th > Age </th>
                <th >Password</th>
                <th >Edit</th>
                <th >Delete</th>
              </tr>
            </thead>

            <tbody>
              {
                this.state.users.map(user =>
                  <tr key={user.id}>
                    <td>{user.fullname}</td>
                    <td>{user.email}</td>
                    <td>{user.age}</td>
                    <td>{user.password}</td>
                    <td>
                      <button onClick={(e) => this.edit(user.id)} type="submit" name="action">
                        Edit</button>
                    </td>
                    <td>
                      <button onClick={(e) => this.delete(user.id)} type="submit" name="action">
                        Delete</button>
                    </td>
                  </tr>
                )
              }
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default App;