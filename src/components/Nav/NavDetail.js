import React from "react";
import styled from "styled-components";

export default function NavDetail(props){

    const pcategory = props.pcategory

    return(
        
        <Positioner>
            <WhiteBackground>
                <NavDetailContents>
                    <Spacer />
                    {pcategory}
                </NavDetailContents>
            </WhiteBackground>
        </Positioner>
        
        
    )
}


const Positioner = styled.div`
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 150px;
    left : calc(50vw - 600px);
    width: 1200px;
    height: 0px;
    z-index:99;

`;

// 회색 배경
const WhiteBackground = styled.div`
    width: 100%;
    height: 50px;
    
    flex-direction: row;
    align-items: center;
    background: #ffffff;

    

`
// NavDetail 콘텐츠
const NavDetailContents = styled.div`
    width: 1200px;
    height: 30px;
    display : inline-grid;
    flex-direction: row;
    align-items: right;

    grid-template-columns: 220px 150px 150px 150px 150px;

`

const Spacer = styled.div`

`