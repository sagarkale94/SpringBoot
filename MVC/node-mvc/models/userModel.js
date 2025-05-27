const users = [
  { id: 1, name: "Alice" },
  { id: 2, name: "Bob" },
];

exports.getAllUsers = () => users;

exports.addUser = (name) => {
  users.push({ id: users.length + 1, name });
};
