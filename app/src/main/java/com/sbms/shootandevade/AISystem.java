package com.sbms.shootandevade;

import java.util.Random;

/**
 * Created by Sherdon on 12/17/2015.
 */
public class AISystem {



    /**
     * Created by Sherdon on 3/23/2015.
     */


        private int IndexX;
        private int IndexY;
        private int IndexZ;
        private int EnemyY;
        private int EnemyZ;
        private int PlayerY;
        private int PlayerZ;
        private int OpticalY;
        private int OpticalZ;
        private int EnemyX;
        private int PlayerX;
        private int OpticalX;

        //random generator
        Random r = new Random();
/*

gameloop()
{
       AISystem.setEnemyDirectionY(5);
       AISystem.setEnemyDirectionX(7);
       AISystem.setEnemyPlayerY(1);
       AISystem.setEnemyPlayerX(2);
       AISystem.setEnemyOpticalY(5);
       AISystem.setEnemyOpticalX(5);
}
 */

        //only for the Y axes
        //This function will help the non playable characters find the player and go to that location
        public void setMovementLengthY(int I)
        {
            IndexY = I;
        }

        //find the non playable characters location
        public void setEnemyDirectionY(int E)
        {
            EnemyY = E;
        }

        //find the players location
        public void setPlayerDirectionY(int P)
        {
            PlayerY = P;
        }

        //find the optical location
        public void setOpticalDirectionY(int O)
        {
            OpticalY = O;
        }

        public int getTrackingLocationY() {
            // TODO Auto-generated method stub

            if (EnemyY >= PlayerY){
                return -IndexY;
            }
            else if (EnemyY <= PlayerY){
                return IndexY;
            }
            return 0;
        }

        //only for the X axes
        //This function will help the non playable characters find the player and go to that location
        public void setMovementLengthX(int I)
        {
            IndexX = I;
        }

        //find the non playable characters location
        public void setEnemyDirectionX(int E)
        {
            EnemyX = E;
        }

        //find the players location
        public void setPlayerDirectionX(int P)
        {
            PlayerX = P;
        }

        //find the optical location
        public void setOpticalDirectionX(int O)
        {
            OpticalX = O;
        }

        public int getTrackingLocationX() {
            // TODO Auto-generated method stub

            if (EnemyX >= PlayerX){
                return -IndexX;
            }
            else if (EnemyX <= PlayerX){
                return IndexX;
            }
            return 0;
        }

        //only for the Z axes
        //This function will help the non playable characters find the player and go to that location
        public void setMovementLengthZ(int I)
        {
            IndexZ = I;
        }

        //find the non playable characters location
        public void setEnemyDirectionZ(int E)
        {
            EnemyZ = E;
        }

        //find the players location
        public void setPlayerDirectionZ(int P)
        {
            PlayerZ = P;
        }

        //find the optical location
        public void setOpticalDirectionZ(int O)
        {
            OpticalZ = O;
        }

        public int getTrackingLocationZ() {
            // TODO Auto-generated method stub

            if (EnemyZ >= PlayerZ){
                return -IndexZ;
            }
            else if (EnemyZ <= PlayerZ){
                return IndexZ;
            }
            return 0;
        }

        //only for the X axes
        //this function will determine which player and/or object is closes to the Non playable character
        public double distanceBetweenPlayer2D()
        {
            double rx = PlayerX - EnemyX;  //horizontal difference
            double ry = PlayerY - EnemyY;  //vertical difference

            if (rx < 0)
            {
                rx = rx * -1;
            }

            if (ry < 0)
            {
                ry = ry * -1;
            }

            double dist = Math.sqrt(rx * rx + ry * ry); //distance using Pythagoras theorem

            return dist;
        }

        public double distanceBetweenPlayer3D()
        {
            double rx = PlayerX - EnemyX;  //horizontal difference
            double ry = PlayerY - EnemyY;  //vertical difference
            double rz = PlayerZ - EnemyZ;

            if (rx < 0)
            {
                rx = rx * -1;
            }

            if (ry < 0)
            {
                ry = ry * -1;
            }

            if (rz < 0)
            {
                rz = rz * -1;
            }

            double dist = Math.sqrt(rx * rx + ry * ry + rz * rz); //distance using Pythagoras theorem

            return dist;
        }

        public double distanceBetweenOptical2D()
        {
            double rx = OpticalX - EnemyX;  //horizontal difference
            double ry = OpticalY - EnemyY;  //vertical difference

            if (rx < 0)
            {
                rx = rx * -1;
            }

            if (ry < 0)
            {
                ry = ry * -1;
            }

            double dist = Math.sqrt(rx * rx + ry * ry); //distance using Pythagoras theorem

            return dist;
        }

        public double distanceBetweenOptical3D()
        {
            double rx = OpticalX - EnemyX;  //horizontal difference
            double ry = OpticalY - EnemyY;  //vertical difference
            double rz = OpticalZ - EnemyZ;

            if (rx < 0)
            {
                rx = rx * -1;
            }

            if (ry < 0)
            {
                ry = ry * -1;
            }

            if (rz < 0)
            {
                rz = rz * -1;
            }

            double dist = Math.sqrt(rx * rx + ry * ry + rz * rz); //distance using Pythagoras theorem

            return dist;
        }

        // public double distanceBetweenPlayer()
        // {

        //  double dx = p - q.x;         //horizontal difference
        //  double dy = p.y - q.y;         //vertical difference
        //  double dist = Math.sqrt(dx * dx + dy * dy); //distance using Pythagoras theorem
        //    return dist;
        //  }

        // public double distanceBetweenEnemy()
        //{
        //      return 0;
        //  }
        //

        //this will tell the NPC where to go and interact with the environment
        //
        public int getAvoidingOpticalY()
        {
            int randomNum = r.nextInt(5 - 2)+2;

            if(distanceBetweenOptical3D() == randomNum)
            {
                if(EnemyY < OpticalY)
                {
                    return -IndexY;
                }
                else if(EnemyY > OpticalY)
                {
                    return IndexY;
                }
            }
            return 0;//remove soon
        }

        public int getAvoidingOpticalX()
        {
            int randomNum = r.nextInt(5 - 2)+2;

            if(distanceBetweenOptical3D() == randomNum)
            {
                if(EnemyX < OpticalX)
                {
                    return -IndexX;
                }
                else if(EnemyX > OpticalX)
                {
                    return IndexX;
                }
            }
            return 0;//remove soon
        }

        public int getAvoidingOpticalZ()
        {
            int randomNum = r.nextInt(5 - 2)+2;

            if(distanceBetweenOptical3D() == randomNum)
            {
                if(EnemyZ < OpticalZ)
                {
                    return -IndexZ;
                }
                else if(EnemyZ > OpticalZ)
                {
                    return IndexZ;
                }
            }
            return 0;//remove soon
        }



        public int getMovingFunction()
        {

            return 0;
        }





        //this function will tell the Non playable characters to stay away or avoid the optical.


        //set if to avoid obticals



}
