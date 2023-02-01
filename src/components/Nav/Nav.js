import React , {useState} from "react";
import styled ,{css} from "styled-components";

/*nav바 밑에 요소 불러오기 */
import NavDetail from "./NavDetail";


export default function Nav(){

    const [pcategory , setPcategory] = useState(null)

    const clickPcategory = (pcategory) => {
        setPcategory(pcategory)
    }
    
    const ContainerSelected = {
        background : '#e8dc8a',
        borderRadius : '24px'
    }
    
    if (pcategory === 'best'){
        console.log(ContainerSelected)
    }
    console.log(pcategory)
    return(
        <Positioner>
            <WhiteBackground>
                <NavContents>
                    <Spacer />
                    <Container 
                        style={pcategory === 'best' ? {background : ContainerSelected.background , borderRadius : ContainerSelected.borderRadius} : {}} 
                        onClick={()=>clickPcategory('best')}>
                        <P>추천상품</P>
                    </Container>

                    <Container 
                        style={pcategory === 'dog' ? {background : ContainerSelected.background , borderRadius : ContainerSelected.borderRadius} : {}} 
                        onClick={()=>clickPcategory('dog')}>
                        <P>강아지</P>
                    </Container>

                    <Container 
                        style={pcategory === 'cat' ? {background : ContainerSelected.background , borderRadius : ContainerSelected.borderRadius} : {}} 
                        onClick={()=>clickPcategory('cat')}>
                        <P>고양이</P>
                    </Container>
                    
                    <Container 
                        style={pcategory === '고라니' ? {background : ContainerSelected.background , borderRadius : ContainerSelected.borderRadius} : {}} 
                        onClick={()=>clickPcategory('고라니')}>
                        <P>고라니</P>
                    </Container>
                </NavContents>
                
                <NavDetail pcategory={pcategory} />
            </WhiteBackground>
        </Positioner>

    )
}

const Positioner = styled.div`
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 100px;
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
// Nav 콘텐츠
const NavContents = styled.div`
    width: 1200px;
    height: 30px;
    display : inline-grid;
    flex-direction: row;
    align-items: right;

    grid-template-columns: 220px 150px 150px 150px 150px;

`

const Container = styled.button`
    background: #ffffff;
    border: 0px;
    font-size : 1rem;

    text-align: center;


    &:hover{
        background-color: #e8dc8a;
        border-radius : 24px;
    }

`

const P = styled.p`
font-family : 'tway';

`

const Spacer = styled.div`

`