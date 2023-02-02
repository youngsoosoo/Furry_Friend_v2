import React from "react";
import styled from "styled-components";

// export default function NavButton(){

// }


export default function NavDetail(props){

    const pcategory = props.pcategory


    return(
        
        <Positioner>
            <WhiteBackground>
                <NavDetailContents>
                    <Spacer />
                    <Button>
                    {pcategory}
                    </Button>
                    <Button>
                    {pcategory}
                    </Button>
                    <Button>
                    {pcategory}
                    </Button>
                    <Button>
                    {pcategory}
                    </Button>
                    <Spacer />
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

    grid-template-columns: 300px 150px 150px 150px 150px 300px;

`

const Button = styled.button`
width: 60px;
height: 60px;

border: 3px solid #ff9dc5;
border-radius: 20px;
box-shadow: 1px 1px 2px 1px rgba(0, 0, 0, 0.1);

background : #fae1f6;
`

const Spacer = styled.div`

`