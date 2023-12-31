import React, { useState, useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/pages/driver/Login.js';
import Register from './components/pages/driver/Register.js';
import Perfil from './components/pages/driver/Perfil';
import Details from './components/pages/driver/Details';
import Parks from './components/pages/driver/Parks';

import Admin from './components/pages/admin/Admin';
import AdminDetails from './components/pages/admin/AdminDetails.js';

import Manager from './components/pages/manager/Manager';
import ManagerDetails from './components/pages/manager/ManagerDetails';
import CreateAdmin from './components/objects/CreateAdmin';

import Programmer from './components/pages/programmer/Programmer';
import CreateManager from './components/objects/CreateGestor';
import CreatePark from './components/objects/CreatePark';


const reservations = [
    {
        "id": 0,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" : "Pagamento Pendente",
        "custo" : 4.35,
        "pago" : false,
        "tipo_lugar" : "Agendado",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-06-22 15:30",
        "data_fim": "2023-06-24 20:42"
    },
    {
        "id": 1,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" : "Agendada",
        "custo" : 1.52,
        "pago" : true,
        "tipo_lugar" : "Agendado",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-06-20 9:30",
        "data_fim": "2023-06-20 9:42"
    },
    {
        "id": 2,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" :"Ocupada",
        "custo" : 31.52,
        "pago" : true,
        "tipo_lugar" : "Agendado",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-03-17 7:30",
        "data_fim": "2023-03-17 20:02"
    },
    {
        "id": 3,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" :"Ocupada",
        "custo" : 31.52,
        "pago" : false,
        "tipo_lugar" : "Instantaneo",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-03-17 7:30",
        "data_fim": "2023-03-17 20:02"
    },
    {
        "id": 4,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" : "Concluida",
        "custo" : 3.11,
        "pago" : true,
        "tipo_lugar" : "Agendado",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-01-20 19:30",
        "data_fim": "2023-01-20 20:42"
    },
    {
        "id": 5,
        "nome_utilizador" : "Ricardo Gomes",
        "nome_parque" : "PARQUE VISCONDE DO RAIO",
        "estado" : "Concluida",
        "custo" : 3.11,
        "pago" : true,
        "tipo_lugar" : "Instantaneo",
        "matricula" : "XA-21-AZ",
        "data_inicio": "2023-01-20 19:30",
        "data_fim": "2023-01-20 20:42"
    }
]


const parques = [
    {
        'id': 0,
        'nome': "PARQUE VISCONDE DO RAIO",
        'morada': "rua dos reis",
        'distancia': "(797 m)",
        'instantaneos_livres': 45,
        'instantaneos_total': 93,
        'lugares_totais': 96,
        'caminho_foto': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 1.30,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
        'instant':"PG-58-KL\nDH-90-FDW",

    },
    {
        'id': 1,
        'nome': "B&B BRAGA LAMAÇÃES",
        'morada': "rua dos reis",
        'distancia': "(2.7 km)",
        'instantaneos_livres': 22,
        'instantaneos_total': 40,
        'lugares_totais': 51,
        'caminho_foto': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 7.00,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
        'instant':"PG-58-KL\nDH-90-FD",

    },
    {
        'id': 2,
        'nome': "BRAGA PARQUE",
        'morada': "rua dos reis",
        'distancia': "(1.1km)",
        'instantaneos_livres': 186,
        'instantaneos_total': 230,
        'lugares_totais': 268,
        'caminho_foto': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 2.15,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
        'instant':"PG-58-KL\nDH-90-FD",
    },
]

const administradores = [
    {
        'id': 0,
        'nome': "joao",
        'email': "joao@gmail.com",
        'telemovel': "936978575",
        'password': "joao",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
    {
        'id': 1,
        'nome': "miguel",
        'email': "miguel@gmail.com",
        'telemovel': "936978575",
        'password': "miguel",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
    {
        'id': 2,
        'nome': "antonio",
        'email': "antonio@gmail.com",
        'telemovel': "936978575",
        'password': "antonio",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
]

const managers = [
    {
        'id': 0,
        'nome': "rui",
        'email': "rui@gmail.com",
        'telemovel': "936978575",
        'password': "rui",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
        'administradores': ["joao","miguel","antonio"],
    },
    {
        'id': 1,
        'nome': "carlos",
        'email': "carlos@gmail.com",
        'telemovel': "936978575",
        'password': "carlos",
        'parques': ["Alex_Nao","Sabe_Conduzir"],
        'administradores': ["Sr. Qual","Sr. Alex","Sr. ???"],
    },
    {
        'id': 2,
        'nome': "pedro",
        'email': "pedro@gmail.com",
        'telemovel': "936978575",
        'password': "pedro",
        'parques': ["esquina",],
        'administradores': ["pedro2","rui2","antonio2"],
    },
]

const estatisticas = [
    {
        'id': 0,
        'nome': "PARQUE VISCONDE DO RAIO",
        'nr': '5',
    },
    {
        'id': 1,
        'nome': "B&B BRAGA LAMAÇÃES",
        'nr': '5',
    },
    {
        'id': 2,
        'nome': "BRAGA PARQUE",
        'nr': '5',
    },
]


const users = [
    {
        'id': 1234567890,
        'nome': "Pedro Jorge",
        'email': "pedrojorge@gmail.com",
        'telemovel': "936978575",
        'password': "pedroJ",
        'nif' : '123456789',
    },
]



function App() {
    const [user,setUser] = useState(JSON.parse(localStorage.getItem('user')));
    const [parkTypeFilter, setParkTypeFilter] = useState('');
    const [displayFilter, setDisplayFilter] = useState(false);
    

    return (
        <Router>
            <Routes>
                {/*Common user*/}
                <Route path='/login' element={<Login/>} />
                <Route path='/register' element={<Register/>} />
                <Route path='/' element={<Parks parques={parques} displayFilter={displayFilter} setDisplayFilter={setDisplayFilter} setParkTypeFilter={setParkTypeFilter} parkTypeFilter={parkTypeFilter}/>} />
                {user['tipo_user'] === 'driver'?
                    <>
                        <Route path='/details' element={<Details setParkTypeFilter={setParkTypeFilter}/>} />
                        <Route path='/perfil' element={<Perfil setParkTypeFilter={setParkTypeFilter} reservations={reservations}/>} />
                    </>
                    :
                    null
                }


                {/*Admin*/}
                {user['tipo_user'] === 'admin'?
                    <>
                        <Route path='/admin' element={<Admin parks={parques} parques={parques}/>}/>
                        <Route path='/admin/details' element={<AdminDetails/>}/>
                    </>
                    :
                    null
                }


                {/*Manager*/}
                {user['tipo_user'] === 'manager'?
                    <>
                        <Route path='/manager' element={<Manager parques={parques} estatisticas={estatisticas} administradores={administradores} parks={parques} selected={1}/>}/>
                        <Route path='/manager/admins' element={<Manager parques={parques} estatisticas={estatisticas} administradores={administradores} selected={2}/>}/>
                        <Route path='/manager/statistics' element={<Manager parques={parques} estatisticas={estatisticas} administradores={administradores} selected={3}/>}/>
                        <Route path='/manager/details' element={<ManagerDetails/>}/>
                        <Route path='/manager/admin/create' element={<CreateAdmin selected={2}/>}/>
                    </>
                    :
                    null
                }


                {/*Programmer*/}
                <Route path='/programmer' element={<Programmer managers={managers} parks={parques} selected={1}/>}/>
                <Route path='/programmer/managers' element={<Programmer managers={managers} selected={2}/>}/>
                {/* <Route path='/programmer/details' element={<ProgrammerDetails/>}/> */}
                <Route path='/programmer/managers/create' element={<CreateManager selected={2}/>}/>
                <Route path='/programmer/park/create' element={<CreatePark selected={1}/>}/>
            </Routes>
        </Router>
    );
}

export default App