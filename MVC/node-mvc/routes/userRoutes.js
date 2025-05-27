const express = require("express");
const router = express.Router();
const userController = require("../controllers/userController");

router.get("/", userController.index);
router.get("/users", userController.getUsers);
router.post("/users", userController.addUser);

module.exports = router;
