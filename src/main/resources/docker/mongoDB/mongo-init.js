db.createUser({
  user: 'thimilo',
  pwd: '123456',
  roles: [
    {
      role: 'dbOwner',
      db: 'gestor-pedido',
    },
  ],
});
