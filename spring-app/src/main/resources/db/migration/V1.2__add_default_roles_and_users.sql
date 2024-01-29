-- ADD PERMISSIONS
insert into permission(
    id,
    permission_name
)
values
    (1, 'sysadm'),
    (2, 'role:manager'),
    (3, 'user:manager');

-- ADD ROLES
insert into role (
    id,
    name
) values
    (1, 'user'),
    (2, 'admin');

-- ADD ROLE-PERMISSION
insert into role_permission(
    role_id,
    permission_id
)
values
    (1, 2),
    (1, 3),
    (2, 1);

-- ADD DEFAULT USERS

insert into users(
    id,
    first_name,
    last_name,
    login,
    password,
    email,
    image,
    registration_date,
    status_id,
    role_id,
    created_at,
    updated_at
)
values
    (1, 'Dmytro', 'Zhurav', 'Zhurawell', '1111', 'zhurawell@gmail.com', null, now(), 1, 2, now(), null),
    (2, 'Test', 'User', 'Test', '1111', 'tester@gmail.com', null, now(), 1, 1, now(), null);