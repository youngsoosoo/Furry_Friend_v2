import React ,{useState} from 'react'
import styled from 'styled-components';

export default function Top({ScrollActive}){
    const [login,setLogin] = useState(false)


    return(
        <Positioner className={ScrollActive ? 'flexible' : null}>
            <GreyBackground>
            <TopContents>


                {login === false ? 
                <>
                <Ul>
                <Li><P>회원가입</P></Li>
                <Li><P>로그인</P></Li>
                </Ul>
                </>
                :
                // 로그인 했을 때
                <>
                <P></P>
                <P>로그아웃</P>
                </>}
            </TopContents>
            </GreyBackground>
        </Positioner>
    )
}

// 상단 고정
const Positioner = styled.div`
    position: sticky;

    display: flex;
    flex-direction: column;

    width: 100vw;
    height: 45px;

    z-index:99;

    background : #FFFFFF;

    padding : 0px;
    border: 0px;    
    margin-top: -10px;
    margin-left: -5px;
    &.flexible{

        position: fixed;
        background : #FFFFFF;
        height: 40px;
        margin-top: -15px;

    }
`;



// 회색 배경
const GreyBackground = styled.div`

    flex-direction: row;
    align-items: center;

`

const TopContents = styled.div`
    width: 1200px;
    height: 20px;
    display: inline-block;
    flex-direction: row;
    align-items: right;

`

const Ul = styled.ul`

position: absolute;
right: calc(50vw - 420px );
list-style : none;


`

const Li = styled.li`
    display: inline;
    margin-left : 20px;
    
`

//글씨
const P = styled.span`
margin: 5px;
font-family : 'tway';
width: 200px;
`