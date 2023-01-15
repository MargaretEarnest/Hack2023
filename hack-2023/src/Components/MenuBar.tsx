import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import {Avatar, IconButton, Menu, MenuItem} from "@mui/material";
import LoginDialog from "./LoginDialog";

function MenuBar(props: { username: string, setUsername: any, setEmail: any, accountType: any, setAccountType: any }) {
    const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    const [loginOpen, setLoginOpen] = React.useState(false);
    const handleLoginClickOpen = () => {
        setLoginOpen(true);
    };
    const handleLoginClose = () => {
        setLoginOpen(false);
    };

    return (
        <AppBar position="static">
            <LoginDialog open={loginOpen} handleClose={handleLoginClose} setEmail={props.setEmail} setUsername={props.setUsername} setAccountType={props.setAccountType}/>
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="#/"
                        sx={{
                            mr: 2,
                            display: {xs: 'none', md: 'flex'},
                            fontFamily: 'Arial',
                            fontWeight: 700,
                            letterSpacing: '0',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        reSearch_
                    </Typography>

                    <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}/>
                    <Typography
                        variant="h5"
                        noWrap
                        component="a"
                        href={"#/"}
                        sx={{
                            mr: 2,
                            display: {xs: 'flex', md: 'none'},
                            flexGrow: 1,
                            fontFamily: 'Arial',
                            fontWeight: 700,
                            letterSpacing: '0',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        reSearch_
                    </Typography>

                    {!props.username ?
                        <Button style={{color: "white", position: "absolute", right: 0}}
                                onClick={handleLoginClickOpen}>Login</Button> :
                        <Box sx={{flexGrow: 0, position: "absolute", right: 0}}>
                            <IconButton onClick={handleOpenUserMenu} sx={{p: 0}}>
                                <Avatar alt={props.username} sx={{bgcolor: "#B8D5ED", color: "#0B3D91", fontWeight: "bold"}}
                                        src="/static/images/avatar/1.jpg"/>
                            </IconButton>
                            <Menu
                                sx={{mt: '45px'}}
                                id="menu-appbar"
                                anchorEl={anchorElUser}
                                anchorOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={Boolean(anchorElUser)}
                                onClose={handleCloseUserMenu}
                            >
                                <MenuItem key={"logout"} onClick={() => {
                                    props.setUsername("");
                                    props.setAccountType("");
                                    window.localStorage.clear();
                                    window.location.href = 'index.html#/';
                                    handleCloseUserMenu();
                                }}>
                                    <Typography textAlign="center">Logout</Typography>
                                    </MenuItem>
                                    </Menu>
                                    </Box>
                                }
                            </Toolbar>
                        </Container>
                        </AppBar>
                        );
                    }

export default MenuBar;