const userModel = require("../models/userModel");

exports.index = (req, res) => {
  res.render("index", { title: "Home" });
};

exports.getUsers = (req, res) => {
  const users = userModel.getAllUsers();
  res.render("users", { title: "User List", users });
};

exports.addUser = (req, res) => {
  const name = req.body.name;
  userModel.addUser(name);
  res.redirect("/users");
};
