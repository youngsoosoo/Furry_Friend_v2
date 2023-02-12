import React from "react";
import { useNavigate } from 'react-router-dom';
import styled from "styled-components";

export default function Back(){
    
    const navigate = useNavigate();

    return(
        <BackButton 
            onClick={() => navigate(-1)}
        >
            ←
        </BackButton>
    )
}

const BackButton = styled.button`

background: transparent;

font-size: 33px;

padding: 10px;
margin: 0px;
border: 0px;

display: block;

&:hover{
    background: #e2e2e2;

}

`
