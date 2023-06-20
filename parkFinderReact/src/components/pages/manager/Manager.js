import '../condutor/Details.css'
import NavbarStaff from '../../objects/NavbarStaff';
import { Button } from '../../interactive_items/Button';
import { useEffect, useState } from 'react';
import Contacts from '../../objects/Contacts';

import ParksManager from '../../objects/ParksManager';
import StatsManager from '../../objects/StatsManager';

function Manager({
    parques,
    administradores,
    estatisticas,
    selected,
}) {

    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(
                    <ParksManager parques={parques}/>
                );
                break;

            case 2:
                setPage(
                    <Contacts
                        listUsers={administradores} 
                        createButton={<Button buttonStyle={"default"} onClick={() => console.log("createButton")}>Criar Administrador</Button>} 
                        editButton={<Button buttonStyle={"default"} onClick={() => console.log("editButton")}>Editar</Button>} 
                        removeButton={<Button buttonStyle={"default"} onClick={() => console.log("removeButton")}>Remover</Button>} 
                        title={"Administradores"}
                        showPark={true}
                    />
                );
                break;

            default:
                setPage(
                    <StatsManager stats={estatisticas}/>
                );
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/manager'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} link={'/manager/'}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} link={'/manager/admins'}>Administradores</Button>
                    <Button buttonStyle={"ditails_button"+(selected===3? ' ditails_button_selected':'')} link={'/manager/statistics'}>Estatísticas</Button>
                </div>

                {page}
            </div>
        </div>
    );
}

export default Manager;


